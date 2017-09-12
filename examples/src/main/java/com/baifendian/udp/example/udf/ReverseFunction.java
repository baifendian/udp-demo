package com.baifendian.udp.example.udf;
import java.sql.SQLException;
import java.util.List;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.phoenix.expression.Expression;
import org.apache.phoenix.expression.function.ScalarFunction;
import org.apache.phoenix.parse.FunctionParseNode.Argument;
import org.apache.phoenix.parse.FunctionParseNode.BuiltInFunction;
import org.apache.phoenix.schema.types.PVarchar;
import org.apache.phoenix.schema.types.PDataType;
import org.apache.phoenix.schema.tuple.Tuple;

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

    String sourceStr = (String)PVarchar.INSTANCE.toObject(ptr, strExpression.getSortOrder());

    if (sourceStr == null) {
      return true;
    }

    ptr.set(PVarchar.INSTANCE.toBytes("hello: "+sourceStr));

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
