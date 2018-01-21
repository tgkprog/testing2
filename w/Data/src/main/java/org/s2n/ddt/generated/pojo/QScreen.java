package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QScreen is a Querydsl query type for QScreen
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QScreen extends com.mysema.query.sql.RelationalPathBase<QScreen> {

    private static final long serialVersionUID = 1212540933;

    public static final QScreen Screen = new QScreen("Screen");

    public final NumberPath<Integer> appID = createNumber("AppID", Integer.class);

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final StringPath description = createString("Description");

    public final NumberPath<Integer> screenId = createNumber("ScreenId", Integer.class);

    public final StringPath screenName = createString("ScreenName");

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QScreen> primary = createPrimaryKey(screenId);

    public QScreen(String variable) {
        super(QScreen.class, forVariable(variable), "null", "Screen");
    }

    @SuppressWarnings("all")
    public QScreen(Path<? extends QScreen> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "Screen");
    }

    public QScreen(PathMetadata<?> metadata) {
        super(QScreen.class, metadata, "null", "Screen");
    }

}

