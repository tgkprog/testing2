package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QFunctional is a Querydsl query type for QFunctional
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QFunctional extends com.mysema.query.sql.RelationalPathBase<QFunctional> {

    private static final long serialVersionUID = 2023922972;

    public static final QFunctional Functional = new QFunctional("Functional");

    public final NumberPath<Integer> appID = createNumber("AppID", Integer.class);

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final StringPath description = createString("Description");

    public final NumberPath<Integer> functionalId = createNumber("FunctionalId", Integer.class);

    public final StringPath functionalName = createString("FunctionalName");

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QFunctional> primary = createPrimaryKey(functionalId);

    public QFunctional(String variable) {
        super(QFunctional.class, forVariable(variable), "null", "Functional");
    }

    @SuppressWarnings("all")
    public QFunctional(Path<? extends QFunctional> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "Functional");
    }

    public QFunctional(PathMetadata<?> metadata) {
        super(QFunctional.class, metadata, "null", "Functional");
    }

}

