package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QApplication is a Querydsl query type for QApplication
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QApplication extends com.mysema.query.sql.RelationalPathBase<QApplication> {

    private static final long serialVersionUID = 1817818455;

    public static final QApplication application = new QApplication("APPLICATION");

    public final NumberPath<Integer> appID = createNumber("AppID", Integer.class);

    public final StringPath appName = createString("AppName");

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final StringPath description = createString("Description");

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QApplication> primary = createPrimaryKey(appID);

    public QApplication(String variable) {
        super(QApplication.class, forVariable(variable), "null", "APPLICATION");
    }

    @SuppressWarnings("all")
    public QApplication(Path<? extends QApplication> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "APPLICATION");
    }

    public QApplication(PathMetadata<?> metadata) {
        super(QApplication.class, metadata, "null", "APPLICATION");
    }

}

