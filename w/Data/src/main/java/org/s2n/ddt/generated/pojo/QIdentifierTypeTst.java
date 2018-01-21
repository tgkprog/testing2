package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QIdentifierTypeTst is a Querydsl query type for QIdentifierTypeTst
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QIdentifierTypeTst extends com.mysema.query.sql.RelationalPathBase<QIdentifierTypeTst> {

    private static final long serialVersionUID = -727641575;

    public static final QIdentifierTypeTst IdentifierTypeTst = new QIdentifierTypeTst("IdentifierType_tst");

    public final NumberPath<Integer> appID = createNumber("AppID", Integer.class);

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final StringPath description = createString("Description");

    public final NumberPath<Integer> identifierTypeId = createNumber("IdentifierTypeId", Integer.class);

    public final StringPath indentifierTypeName = createString("IndentifierTypeName");

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QIdentifierTypeTst> primary = createPrimaryKey(identifierTypeId);

    public QIdentifierTypeTst(String variable) {
        super(QIdentifierTypeTst.class, forVariable(variable), "null", "IdentifierType_tst");
    }

    @SuppressWarnings("all")
    public QIdentifierTypeTst(Path<? extends QIdentifierTypeTst> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "IdentifierType_tst");
    }

    public QIdentifierTypeTst(PathMetadata<?> metadata) {
        super(QIdentifierTypeTst.class, metadata, "null", "IdentifierType_tst");
    }

}

