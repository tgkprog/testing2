package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QDeb is a Querydsl query type for QDeb
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QDeb extends com.mysema.query.sql.RelationalPathBase<QDeb> {

    private static final long serialVersionUID = 443493128;

    public static final QDeb deb = new QDeb("deb");

    public final NumberPath<Integer> appID = createNumber("AppID", Integer.class);

    public final StringPath appName = createString("AppName");

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final StringPath description = createString("Description");

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QDeb> primary = createPrimaryKey(appID);

    public QDeb(String variable) {
        super(QDeb.class, forVariable(variable), "null", "deb");
    }

    @SuppressWarnings("all")
    public QDeb(Path<? extends QDeb> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "deb");
    }

    public QDeb(PathMetadata<?> metadata) {
        super(QDeb.class, metadata, "null", "deb");
    }

}

