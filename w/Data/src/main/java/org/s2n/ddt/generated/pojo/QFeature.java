package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QFeature is a Querydsl query type for QFeature
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QFeature extends com.mysema.query.sql.RelationalPathBase<QFeature> {

    private static final long serialVersionUID = 323438205;

    public static final QFeature Feature = new QFeature("Feature");

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final StringPath description = createString("Description");

    public final NumberPath<Integer> featureId = createNumber("FeatureId", Integer.class);

    public final StringPath featureName = createString("FeatureName");

    public final NumberPath<Integer> functionalId = createNumber("FunctionalId", Integer.class);

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QFeature> primary = createPrimaryKey(featureId);

    public QFeature(String variable) {
        super(QFeature.class, forVariable(variable), "null", "Feature");
    }

    @SuppressWarnings("all")
    public QFeature(Path<? extends QFeature> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "Feature");
    }

    public QFeature(PathMetadata<?> metadata) {
        super(QFeature.class, metadata, "null", "Feature");
    }

}

