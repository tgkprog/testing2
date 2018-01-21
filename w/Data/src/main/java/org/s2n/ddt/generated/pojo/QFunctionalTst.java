package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QFunctionalTst is a Querydsl query type for QFunctionalTst
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QFunctionalTst extends com.mysema.query.sql.RelationalPathBase<QFunctionalTst> {

    private static final long serialVersionUID = 1938442009;

    public static final QFunctionalTst FunctionalTst = new QFunctionalTst("Functional_tst");

    public final NumberPath<Integer> appID = createNumber("AppID", Integer.class);

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final StringPath description = createString("Description");

    public final NumberPath<Integer> functionalId = createNumber("FunctionalId", Integer.class);

    public final StringPath functionalName = createString("FunctionalName");

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QFunctionalTst> primary = createPrimaryKey(functionalId);

    public QFunctionalTst(String variable) {
        super(QFunctionalTst.class, forVariable(variable), "null", "Functional_tst");
    }

    @SuppressWarnings("all")
    public QFunctionalTst(Path<? extends QFunctionalTst> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "Functional_tst");
    }

    public QFunctionalTst(PathMetadata<?> metadata) {
        super(QFunctionalTst.class, metadata, "null", "Functional_tst");
    }

}

