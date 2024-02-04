package actions.views;

import java.util.ArrayList;
import java.util.List;

import constants.AttributeConst;
import constants.JpaConst;
import models.Yoine;

/**
 * いいねのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class YoineConverter {

    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param yv YoineViewのインスタンス
     * @return Yoineのインスタンス
     */
    public static Yoine toModel(YoineView yv) {

        return new Yoine(
                yv.getId(),
                ReportConverter.toModel(yv.getReport()),
                EmployeeConverter.toModel(yv.getEmployee()),
                yv.getCreatedAt(),
                yv.getUpdatedAt(),
                yv.getDeleteFlag() == null
                        ? null
                        : yv.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()
                                ? JpaConst.YOINE_DEL_TRUE
                                : JpaConst.YOINE_DEL_FALSE);
    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param y Yoineのインスタンス
     * @return YoineViewのインスタンス
     */
    public static YoineView toView(Yoine y) {

        if(y == null) {
            return null;
        }

        return new YoineView(
                y.getId(),
                ReportConverter.toView(y.getReport()),
                EmployeeConverter.toView(y.getEmployee()),
                y.getCreatedAt(),
                y.getUpdatedAt(),
                y.getDeleteFlag() == null
                        ? null
                        : y.getDeleteFlag() == JpaConst.YOINE_DEL_TRUE
                                ? AttributeConst.DEL_FLAG_TRUE.getIntegerValue()
                                : AttributeConst.DEL_FLAG_FALSE.getIntegerValue());
    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<YoineView> toViewList(List<Yoine> list) {
        List<YoineView> evs = new ArrayList<>();

        for (Yoine y : list) {
            evs.add(toView(y));
        }

        return evs;
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param y DTOモデル(コピー先)
     * @param yv Viewモデル(コピー元)
     */
    public static void copyViewToModel(Yoine y, YoineView yv) {
        y.setId(yv.getId());
        y.setReport(ReportConverter.toModel(yv.getReport()));
        y.setEmployee(EmployeeConverter.toModel(yv.getEmployee()));
        y.setCreatedAt(yv.getCreatedAt());
        y.setUpdatedAt(yv.getUpdatedAt());
        y.setDeleteFlag(yv.getDeleteFlag());

    }

}
