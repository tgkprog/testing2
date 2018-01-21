package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QUserTst is a Querydsl query type for QUserTst
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QUserTst extends com.mysema.query.sql.RelationalPathBase<QUserTst> {

    private static final long serialVersionUID = 1155502481;

    public static final QUserTst UserTst = new QUserTst("User_tst");

    public final StringPath email = createString("email");

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public QUserTst(String variable) {
        super(QUserTst.class, forVariable(variable), "null", "User_tst");
    }

    @SuppressWarnings("all")
    public QUserTst(Path<? extends QUserTst> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "User_tst");
    }

    public QUserTst(PathMetadata<?> metadata) {
        super(QUserTst.class, metadata, "null", "User_tst");
    }

}

