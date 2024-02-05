package actions;

import java.io.IOException;

import javax.servlet.ServletException;

import actions.views.EmployeeView;
import actions.views.ReportView;
import actions.views.YoineView;
import constants.AttributeConst;
import services.YoineService;

/**
 * 従業員に関わる処理を行うActionクラス
 *
 */
public class YoineAction extends ActionBase {

    private YoineService service;

    /**
     * メソッドを実行する
     */
    @Override
    public void process() throws ServletException, IOException {

        service = new YoineService();

        //メソッドを実行
        invoke();

        service.close();
    }

    /**
     * 新規登録を行う
     * @throws ServletException
     * @throws IOException
     */
    public void create() throws ServletException, IOException {

        //CSRF対策 tokenのチェック
        if (checkToken()) {


            //日報のidを取得
            ReportView rv = (ReportView) getSessionScope(AttributeConst.REPORT);

            //セッションからログイン中の従業員情報を取得
            EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

            //パラメータの値をもとにいいね情報のインスタンスを作成する
            YoineView yv = new YoineView(
                    null,
                    rv,
                    ev,
                    null,
                    null,
                    AttributeConst.DEL_FLAG_FALSE.getIntegerValue());

            //日報情報登録
            service.create(yv);

            //詳細画面にリダイレクト
//            redirect(ForwardConst.ACT_REP, ForwardConst.CMD_SHOW);

        }
    }


}