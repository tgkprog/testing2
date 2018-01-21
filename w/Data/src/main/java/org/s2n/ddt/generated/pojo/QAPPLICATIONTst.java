package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QAPPLICATIONTst is a Querydsl query type for QAPPLICATIONTst
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QAPPLICATIONTst extends com.mysema.query.sql.RelationalPathBase<QAPPLICATIONTst> {

    private static final long serialVersionUID = -1791650562;

    public static final QAPPLICATIONTst APPLICATIONTst = new QAPPLICATIONTst("APPLICATION_tst");

    public final NumberPath<Integer> appID = createNumber("AppID", Integer.class);

    public final StringPath appName = createString("AppName");

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final StringPath description = createString("Description");

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QAPPLICATIONTst> primary = createPrimaryKey(appID);

    public QAPPLICATIONTst(String variable) {
        super(QAPPLICATIONTst.class, forVariable(variable), "null", "APPLICATION_tst");
    }

    @SuppressWarnings("all")
    public QAPPLICATIONTst(Path<? extends QAPPLICATIONTst> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "APPLICATION_tst");
    }

    public QAPPLICATIONTst(PathMetadata<?> metadata) {
        super(QAPPLICATIONTst.class, metadata, "null", "APPLICATION_tst");
    }

}

