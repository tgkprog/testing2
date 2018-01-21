package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QChild is a Querydsl query type for QChild
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QChild extends com.mysema.query.sql.RelationalPathBase<QChild> {

    private static final long serialVersionUID = 994309731;

    public static final QChild child = new QChild("child");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> parentId = createNumber("parent_id", Integer.class);

    public final com.mysema.query.sql.ForeignKey<QParent> childIbfk1 = createForeignKey(parentId, "id");

    public QChild(String variable) {
        super(QChild.class, forVariable(variable), "null", "child");
    }

    @SuppressWarnings("all")
    public QChild(Path<? extends QChild> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "child");
    }

    public QChild(PathMetadata<?> metadata) {
        super(QChild.class, metadata, "null", "child");
    }

}

