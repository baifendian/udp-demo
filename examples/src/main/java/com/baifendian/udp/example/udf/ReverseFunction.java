package com.baifendian.udp.example.udf;
import java.sql.SQLException;
import java.util.List;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.phoenix.expression.Expression;
import org.apache.phoenix.expression.function.ScalarFunction;
import org.apache.phoenix.parse.FunctionParseNode.Argument;
import org.apache.phoenix.parse.FunctionParseNode.BuiltInFunction;
import org.apache.phoenix.schema.types.PLong;
import org.apache.phoenix.schema.types.PVarchar;
import org.apache.phoenix.schema.types.PDataType;
import org.apache.phoenix.schema.tuple.Tuple;

/**
 * 函数的输入参数为：bigInt，输入为: varchar
 */
@BuiltInFunction(name=ReverseFunction.NAME,  args={
    @Argument(allowedTypes={PVarchar.class})} )
public class ReverseFunction extends ScalarFunction {
  public static final String NAME = "URLPARSE";

  public ReverseFunction() {
  }

  public ReverseFunction(List children) throws SQLException {
    super(children);
  }

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public boolean evaluate(Tuple tuple, ImmutableBytesWritable ptr) {
    Expression strExpression = getStrExpression();
    if (!strExpression.evaluate(tuple, ptr)) {
      return false;
    }

    Long id = (Long) PLong.INSTANCE.toObject(ptr, strExpression.getSortOrder());

    if (id == null) {
      return true;
    }

    ptr.set(PLong.INSTANCE.toBytes(id*2L));

    return true;
  }

  @Override
  public PDataType getDataType() {
    return getStrExpression().getDataType();
  }

  @Override
  public boolean isNullable() {
    return getStrExpression().isNullable();
  }

  private Expression getStrExpression() {
    return children.get(0);
  }
}
