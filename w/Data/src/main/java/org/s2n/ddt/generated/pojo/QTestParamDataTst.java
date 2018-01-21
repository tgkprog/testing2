package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTestParamDataTst is a Querydsl query type for QTestParamDataTst
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QTestParamDataTst extends com.mysema.query.sql.RelationalPathBase<QTestParamDataTst> {

    private static final long serialVersionUID = -891288951;

    public static final QTestParamDataTst TestParamDataTst = new QTestParamDataTst("TestParamData_tst");

    public final NumberPath<Integer> appID = createNumber("AppID", Integer.class);

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final NumberPath<Integer> paramId = createNumber("ParamId", Integer.class);

    public final StringPath paramValue = createString("ParamValue");

    public final NumberPath<Integer> testCaseID = createNumber("TestCaseID", Integer.class);

    public final NumberPath<Integer> testParamDataId = createNumber("TestParamDataId", Integer.class);

    public final StringPath testSet = createString("TestSet");

    public final NumberPath<Integer> testStepID = createNumber("TestStepID", Integer.class);

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final SimplePath<byte[]> valueBig = createSimple("ValueBig", byte[].class);

    public final com.mysema.query.sql.PrimaryKey<QTestParamDataTst> primary = createPrimaryKey(testParamDataId);

    public QTestParamDataTst(String variable) {
        super(QTestParamDataTst.class, forVariable(variable), "null", "TestParamData_tst");
    }

    @SuppressWarnings("all")
    public QTestParamDataTst(Path<? extends QTestParamDataTst> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "TestParamData_tst");
    }

    public QTestParamDataTst(PathMetadata<?> metadata) {
        super(QTestParamDataTst.class, metadata, "null", "TestParamData_tst");
    }

}

