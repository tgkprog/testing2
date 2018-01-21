package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTest is a Querydsl query type for QTest
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QTest extends com.mysema.query.sql.RelationalPathBase<QTest> {

    private static final long serialVersionUID = 863862379;

    public static final QTest test = new QTest("test");

    public final NumberPath<Integer> testerNo = createNumber("testerNo", Integer.class);

    public QTest(String variable) {
        super(QTest.class, forVariable(variable), "null", "test");
    }

    @SuppressWarnings("all")
    public QTest(Path<? extends QTest> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "test");
    }

    public QTest(PathMetadata<?> metadata) {
        super(QTest.class, metadata, "null", "test");
    }

}

