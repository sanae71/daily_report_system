package services;

import java.time.LocalDateTime;

import actions.views.ReportConverter;
import actions.views.ReportView;
import actions.views.YoineConverter;
import actions.views.YoineView;
import constants.JpaConst;
import models.Yoine;

/**
 * いいねテーブルの操作に関わる処理を行うクラス
 */
public class YoineService extends ServiceBase {

    /**
     * 指定した日報のいいねの件数を取得し、返却する
     * @param employee
     * @return いいねの件数
     */
    public long countAllYoine(ReportView report) {

        long yoine_count = (long) em.createNamedQuery(JpaConst.Q_YOINE_COUNT_ALL_YOINE, Long.class)
                .setParameter(JpaConst.JPQL_PARM_REPORT, ReportConverter.toModel(report))
                .getSingleResult();

        return yoine_count;
    }

    /**
     * 画面から入力されたいいねの登録内容を元にデータを1件作成し、いいねテーブルに登録する
     * @param yv いいねの登録内容
     */
    public void create(YoineView yv) {


        LocalDateTime now = LocalDateTime.now();
        yv.setCreatedAt(now);
        yv.setUpdatedAt(now);
        em.getTransaction().begin();
        em.persist(YoineConverter.toModel(yv));
        em.getTransaction().commit();

    }

    /**
     * idを条件にいいねデータを論理削除する
     * @param id
     */
    public void destroy(Integer id) {

        //idを条件に登録済みの従業員情報を取得する
        YoineView savedYoine = findOne(id);

        //更新日時に現在時刻を設定する
        LocalDateTime today = LocalDateTime.now();
        savedYoine.setUpdatedAt(today);

        //論理削除フラグをたてる
        savedYoine.setDeleteFlag(JpaConst.YOINE_DEL_TRUE);

        //更新処理を行う
        update(savedYoine);

    }

    /**
     * idを条件に取得したデータをYoineViewのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public YoineView findOne(int id) {
        return YoineConverter.toView(findOneInternal(id));
    }

    /**
     * idを条件にデータを1件取得する
     * @param id
     * @return 取得データのインスタンス
     */
    private Yoine findOneInternal(int id) {
        return em.find(Yoine.class, id);
    }

//    /**
//     * 従業員データを1件登録する
//     * @param yv 従業員データ
//     */
//    private void create(YoineView yv) {
//
//        em.getTransaction().begin();
//        em.persist(YoineConverter.toModel(yv));
//        em.getTransaction().commit();
//
//    }

    /**
     * 従業員データを更新する
     * @param ev 画面から入力された従業員の登録内容
     */
    private void update(YoineView yv) {

        em.getTransaction().begin();
        Yoine y = findOneInternal(yv.getId());
        YoineConverter.copyViewToModel(y, yv);
        em.getTransaction().commit();

    }

}
