package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QFeatureTst is a Querydsl query type for QFeatureTst
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QFeatureTst extends com.mysema.query.sql.RelationalPathBase<QFeatureTst> {

    private static final long serialVersionUID = 1936004632;

    public static final QFeatureTst FeatureTst = new QFeatureTst("Feature_tst");

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final StringPath description = createString("Description");

    public final NumberPath<Integer> featureId = createNumber("FeatureId", Integer.class);

    public final StringPath featureName = createString("FeatureName");

    public final NumberPath<Integer> functionalId = createNumber("FunctionalId", Integer.class);

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QFeatureTst> primary = createPrimaryKey(featureId);

    public QFeatureTst(String variable) {
        super(QFeatureTst.class, forVariable(variable), "null", "Feature_tst");
    }

    @SuppressWarnings("all")
    public QFeatureTst(Path<? extends QFeatureTst> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "Feature_tst");
    }

    public QFeatureTst(PathMetadata<?> metadata) {
        super(QFeatureTst.class, metadata, "null", "Feature_tst");
    }

}

