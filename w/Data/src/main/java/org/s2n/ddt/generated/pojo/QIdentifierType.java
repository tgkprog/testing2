package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QIdentifierType is a Querydsl query type for QIdentifierType
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QIdentifierType extends com.mysema.query.sql.RelationalPathBase<QIdentifierType> {

    private static final long serialVersionUID = 1157083676;

    public static final QIdentifierType IdentifierType = new QIdentifierType("IdentifierType");

    public final NumberPath<Integer> appID = createNumber("AppID", Integer.class);

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final StringPath description = createString("Description");

    public final NumberPath<Integer> identifierTypeId = createNumber("IdentifierTypeId", Integer.class);

    public final StringPath indentifierTypeName = createString("IndentifierTypeName");

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QIdentifierType> primary = createPrimaryKey(identifierTypeId);

    public QIdentifierType(String variable) {
        super(QIdentifierType.class, forVariable(variable), "null", "IdentifierType");
    }

    @SuppressWarnings("all")
    public QIdentifierType(Path<? extends QIdentifierType> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "IdentifierType");
    }

    public QIdentifierType(PathMetadata<?> metadata) {
        super(QIdentifierType.class, metadata, "null", "IdentifierType");
    }

}

