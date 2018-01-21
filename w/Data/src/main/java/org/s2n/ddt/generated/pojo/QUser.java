package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QUser is a Querydsl query type for QUser
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QUser extends com.mysema.query.sql.RelationalPathBase<QUser> {

    private static final long serialVersionUID = 863905188;

    public static final QUser User = new QUser("User");

    public final StringPath email = createString("email");

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public QUser(String variable) {
        super(QUser.class, forVariable(variable), "null", "User");
    }

    @SuppressWarnings("all")
    public QUser(Path<? extends QUser> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "User");
    }

    public QUser(PathMetadata<?> metadata) {
        super(QUser.class, metadata, "null", "User");
    }

}

