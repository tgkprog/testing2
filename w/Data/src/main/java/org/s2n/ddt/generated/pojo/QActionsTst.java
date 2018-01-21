package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QActionsTst is a Querydsl query type for QActionsTst
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QActionsTst extends com.mysema.query.sql.RelationalPathBase<QActionsTst> {

    private static final long serialVersionUID = -355486031;

    public static final QActionsTst ActionsTst = new QActionsTst("Actions_tst");

    public final NumberPath<Integer> actionId = createNumber("ActionId", Integer.class);

    public final StringPath actionName = createString("ActionName");

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final StringPath description = createString("Description");

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QActionsTst> primary = createPrimaryKey(actionId);

    public QActionsTst(String variable) {
        super(QActionsTst.class, forVariable(variable), "null", "Actions_tst");
    }

    @SuppressWarnings("all")
    public QActionsTst(Path<? extends QActionsTst> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "Actions_tst");
    }

    public QActionsTst(PathMetadata<?> metadata) {
        super(QActionsTst.class, metadata, "null", "Actions_tst");
    }

}

