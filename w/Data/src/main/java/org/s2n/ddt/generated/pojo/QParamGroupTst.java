package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QParamGroupTst is a Querydsl query type for QParamGroupTst
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QParamGroupTst extends com.mysema.query.sql.RelationalPathBase<QParamGroupTst> {

    private static final long serialVersionUID = 1568913610;

    public static final QParamGroupTst ParamGroupTst = new QParamGroupTst("ParamGroup_tst");

    public final NumberPath<Integer> appID = createNumber("AppID", Integer.class);

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final StringPath description = createString("Description");

    public final NumberPath<Integer> orderBy = createNumber("OrderBy", Integer.class);

    public final NumberPath<Integer> paramGroupId = createNumber("ParamGroupId", Integer.class);

    public final StringPath paramGroupName = createString("ParamGroupName");

    public final StringPath tag = createString("Tag");

    public final NumberPath<Integer> testCaseID = createNumber("TestCaseID", Integer.class);

    public final NumberPath<Integer> testScenarioId = createNumber("TestScenarioId", Integer.class);

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QParamGroupTst> primary = createPrimaryKey(paramGroupId);

    public QParamGroupTst(String variable) {
        super(QParamGroupTst.class, forVariable(variable), "null", "ParamGroup_tst");
    }

    @SuppressWarnings("all")
    public QParamGroupTst(Path<? extends QParamGroupTst> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "ParamGroup_tst");
    }

    public QParamGroupTst(PathMetadata<?> metadata) {
        super(QParamGroupTst.class, metadata, "null", "ParamGroup_tst");
    }

}

