package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QRunner is a Querydsl query type for QRunner
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QRunner extends com.mysema.query.sql.RelationalPathBase<QRunner> {

    private static final long serialVersionUID = 1200424649;

    public static final QRunner Runner = new QRunner("Runner");

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final StringPath description = createString("Description");

    public final NumberPath<Integer> runnerId = createNumber("RunnerId", Integer.class);

    public final StringPath runnerName = createString("RunnerName");

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QRunner> primary = createPrimaryKey(runnerId);

    public QRunner(String variable) {
        super(QRunner.class, forVariable(variable), "null", "Runner");
    }

    @SuppressWarnings("all")
    public QRunner(Path<? extends QRunner> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "Runner");
    }

    public QRunner(PathMetadata<?> metadata) {
        super(QRunner.class, metadata, "null", "Runner");
    }

}

