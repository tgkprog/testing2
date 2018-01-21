package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QRunResult is a Querydsl query type for QRunResult
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QRunResult extends com.mysema.query.sql.RelationalPathBase<QRunResult> {

    private static final long serialVersionUID = 1151541327;

    public static final QRunResult RunResult = new QRunResult("RunResult");

    public final StringPath dateEnded = createString("dateEnded");

    public final StringPath dateStarted = createString("dateStarted");

    public final BooleanPath detailReport = createBoolean("DetailReport");

    public final NumberPath<Integer> runResultId = createNumber("RunResultId", Integer.class);

    public final BooleanPath summaryReport = createBoolean("SummaryReport");

    public final StringPath userName = createString("UserName");

    public final com.mysema.query.sql.PrimaryKey<QRunResult> primary = createPrimaryKey(runResultId);

    public QRunResult(String variable) {
        super(QRunResult.class, forVariable(variable), "null", "RunResult");
    }

    @SuppressWarnings("all")
    public QRunResult(Path<? extends QRunResult> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "RunResult");
    }

    public QRunResult(PathMetadata<?> metadata) {
        super(QRunResult.class, metadata, "null", "RunResult");
    }

}

