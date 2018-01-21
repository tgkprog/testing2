package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QParent is a Querydsl query type for QParent
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QParent extends com.mysema.query.sql.RelationalPathBase<QParent> {

    private static final long serialVersionUID = 1124806723;

    public static final QParent parent = new QParent("parent");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final com.mysema.query.sql.PrimaryKey<QParent> primary = createPrimaryKey(id);

    public final com.mysema.query.sql.ForeignKey<QChild> _childIbfk1 = createInvForeignKey(id, "parent_id");

    public QParent(String variable) {
        super(QParent.class, forVariable(variable), "null", "parent");
    }

    @SuppressWarnings("all")
    public QParent(Path<? extends QParent> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "parent");
    }

    public QParent(PathMetadata<?> metadata) {
        super(QParent.class, metadata, "null", "parent");
    }

}

