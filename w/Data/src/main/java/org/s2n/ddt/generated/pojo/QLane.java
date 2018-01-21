package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QLane is a Querydsl query type for QLane
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QLane extends com.mysema.query.sql.RelationalPathBase<QLane> {

    private static final long serialVersionUID = 863620037;

    public static final QLane Lane = new QLane("Lane");

    public final StringPath agentName = createString("AgentName");

    public final NumberPath<Integer> clones = createNumber("Clones", Integer.class);

    public final NumberPath<Integer> iterations = createNumber("Iterations", Integer.class);

    public final NumberPath<Integer> laneId = createNumber("LaneId", Integer.class);

    public final StringPath laneType = createString("LaneType");

    public final StringPath laneUserName = createString("LaneUserName");

    public final StringPath runnerType = createString("RunnerType");

    public final com.mysema.query.sql.PrimaryKey<QLane> primary = createPrimaryKey(laneId);

    public QLane(String variable) {
        super(QLane.class, forVariable(variable), "null", "Lane");
    }

    @SuppressWarnings("all")
    public QLane(Path<? extends QLane> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "Lane");
    }

    public QLane(PathMetadata<?> metadata) {
        super(QLane.class, metadata, "null", "Lane");
    }

}

