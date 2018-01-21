package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QAgentDetails is a Querydsl query type for QAgentDetails
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QAgentDetails extends com.mysema.query.sql.RelationalPathBase<QAgentDetails> {

    private static final long serialVersionUID = -1580263338;

    public static final QAgentDetails AgentDetails = new QAgentDetails("AgentDetails");

    public final NumberPath<Integer> agentId = createNumber("AgentId", Integer.class);

    public final StringPath agentName = createString("AgentName");

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final StringPath instance = createString("Instance");

    public final StringPath ip = createString("IP");

    public final NumberPath<Integer> port = createNumber("Port", Integer.class);

    public final NumberPath<Integer> testPlanID = createNumber("TestPlanID", Integer.class);

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QAgentDetails> primary = createPrimaryKey(agentId);

    public QAgentDetails(String variable) {
        super(QAgentDetails.class, forVariable(variable), "null", "AgentDetails");
    }

    @SuppressWarnings("all")
    public QAgentDetails(Path<? extends QAgentDetails> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "AgentDetails");
    }

    public QAgentDetails(PathMetadata<?> metadata) {
        super(QAgentDetails.class, metadata, "null", "AgentDetails");
    }

}

