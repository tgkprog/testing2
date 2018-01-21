package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QParam is a Querydsl query type for QParam
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QParam extends com.mysema.query.sql.RelationalPathBase<QParam> {

    private static final long serialVersionUID = 1006115284;

    public static final QParam Param = new QParam("Param");

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final StringPath description = createString("Description");

    public final NumberPath<Integer> objectId = createNumber("ObjectId", Integer.class);

    public final NumberPath<Integer> orderBy = createNumber("OrderBy", Integer.class);

    public final NumberPath<Integer> paramGroupId = createNumber("ParamGroupId", Integer.class);

    public final NumberPath<Integer> paramId = createNumber("ParamId", Integer.class);

    public final StringPath paramName = createString("ParamName");

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QParam> primary = createPrimaryKey(paramId);

    public QParam(String variable) {
        super(QParam.class, forVariable(variable), "null", "Param");
    }

    @SuppressWarnings("all")
    public QParam(Path<? extends QParam> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "Param");
    }

    public QParam(PathMetadata<?> metadata) {
        super(QParam.class, metadata, "null", "Param");
    }

}

