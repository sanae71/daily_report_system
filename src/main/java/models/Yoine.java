package models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 中間データのDTOモデル
 *
 */
@Table(name = JpaConst.TABLE_YOINE)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_YOINE_GET_ALL,
            query = JpaConst.Q_YOINE_GET_ALL_DEF),
    @NamedQuery(
            name = JpaConst.Q_YOINE_COUNT,
            query = JpaConst.Q_YOINE_COUNT_DEF),
    @NamedQuery(
            name = JpaConst.Q_YOINE_GET_ALL_YOINE,
            query = JpaConst.Q_YOINE_GET_ALL_YOINE_DEF),
    @NamedQuery(
            name = JpaConst.Q_YOINE_COUNT_ALL_YOINE,
            query = JpaConst.Q_YOINE_COUNT_ALL_YOINE_DEF)
})

@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
@Entity

public class Yoine {

    /**
     * id
     */
    @Id
    @Column(name = JpaConst.YOINE_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 日報のid
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.YOINE_COL_REP, nullable = false)
    private Report report;

    /**
     * いいねした従業員
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.YOINE_COL_EMP, nullable = false)
    private Employee employee;

    /**
     *登録日時
     */
    @Column(name = JpaConst.YOINE_COL_CREATED_AT, nullable = false)
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    @Column(name = JpaConst.YOINE_COL_UPDATED_AT, nullable = false)
    private LocalDateTime updatedAt;

    /**
     * 削除されたいいねかどうか（現役：0、削除済み：1）
     */
    @Column(name = JpaConst.YOINE_COL_DELETE_FLAG, nullable = false)
    private Integer deleteFlag;

}
