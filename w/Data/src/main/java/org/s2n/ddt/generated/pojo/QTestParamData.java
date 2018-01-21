package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTestParamData is a Querydsl query type for QTestParamData
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QTestParamData extends com.mysema.query.sql.RelationalPathBase<QTestParamData> {

    private static final long serialVersionUID = -1788602452;

    public static final QTestParamData TestParamData = new QTestParamData("TestParamData");

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

    public final com.mysema.query.sql.PrimaryKey<QTestParamData> primary = createPrimaryKey(testParamDataId);

    public QTestParamData(String variable) {
        super(QTestParamData.class, forVariable(variable), "null", "TestParamData");
    }

    @SuppressWarnings("all")
    public QTestParamData(Path<? extends QTestParamData> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "TestParamData");
    }

    public QTestParamData(PathMetadata<?> metadata) {
        super(QTestParamData.class, metadata, "null", "TestParamData");
    }

}

