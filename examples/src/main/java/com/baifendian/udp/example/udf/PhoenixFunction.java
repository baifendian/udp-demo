package com.baifendian.udp.example.udf;

import java.sql.SQLException;
import java.util.List;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.phoenix.expression.Expression;
import org.apache.phoenix.expression.function.ScalarFunction;
import org.apache.phoenix.parse.FunctionParseNode.Argument;
import org.apache.phoenix.parse.FunctionParseNode.BuiltInFunction;
import org.apache.phoenix.schema.types.PDataType;
import org.apache.phoenix.schema.tuple.Tuple;
import org.apache.phoenix.schema.types.PVarchar;

/**
 * 函数的输入参数为：bigInt，输入为: varchar
 */
@BuiltInFunction(name= PhoenixFunction.NAME,  args={
    @Argument(allowedTypes={PVarchar.class})} )
public class PhoenixFunction extends ScalarFunction {
  public static final String NAME = "HELLO";

  public PhoenixFunction() {
  }

  public PhoenixFunction(List children) throws SQLException {
    super(children);
  }

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public boolean evaluate(Tuple tuple, ImmutableBytesWritable ptr) {
    // Get the child argument and evaluate it first
    Expression arg = getChildren().get(0);
    if (!arg.evaluate(tuple, ptr)) {
      return false;
    }

    String sourceStr = (String)PVarchar.INSTANCE.toObject(ptr, arg.getSortOrder());
    if (sourceStr == null) {
      ptr.set(PVarchar.INSTANCE.toBytes("hello: null"));
      return true;
    }
    ptr.set(PVarchar.INSTANCE.toBytes("hello: "+sourceStr));
    return true;
  }

  @Override
  public PDataType getDataType() {
    return getChildren().get(0).getDataType();
  }

  @Override
 public boolean isNullable() {
    return getChildren().get(0).isNullable();
  }
}
