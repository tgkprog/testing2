package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QIdGen is a Querydsl query type for QIdGen
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QIdGen extends com.mysema.query.sql.RelationalPathBase<QIdGen> {

    private static final long serialVersionUID = 999698812;

    public static final QIdGen idGen1 = new QIdGen("ID_GEN");

    public final NumberPath<Long> id = createNumber("Id", Long.class);

    public final NumberPath<Integer> idGen = createNumber("Id_Gen", Integer.class);

    public final StringPath name = createString("Name");

    public final com.mysema.query.sql.PrimaryKey<QIdGen> primary = createPrimaryKey(idGen);

    public QIdGen(String variable) {
        super(QIdGen.class, forVariable(variable), "null", "ID_GEN");
    }

    @SuppressWarnings("all")
    public QIdGen(Path<? extends QIdGen> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "ID_GEN");
    }

    public QIdGen(PathMetadata<?> metadata) {
        super(QIdGen.class, metadata, "null", "ID_GEN");
    }

}

