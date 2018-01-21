package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QParamTst is a Querydsl query type for QParamTst
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QParamTst extends com.mysema.query.sql.RelationalPathBase<QParamTst> {

    private static final long serialVersionUID = -1396248735;

    public static final QParamTst ParamTst = new QParamTst("Param_tst");

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

    public final com.mysema.query.sql.PrimaryKey<QParamTst> primary = createPrimaryKey(paramId);

    public QParamTst(String variable) {
        super(QParamTst.class, forVariable(variable), "null", "Param_tst");
    }

    @SuppressWarnings("all")
    public QParamTst(Path<? extends QParamTst> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "Param_tst");
    }

    public QParamTst(PathMetadata<?> metadata) {
        super(QParamTst.class, metadata, "null", "Param_tst");
    }

}

