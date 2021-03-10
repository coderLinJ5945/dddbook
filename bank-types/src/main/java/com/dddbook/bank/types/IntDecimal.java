//package com.dddbook.bank.types;
//
//import lombok.Getter;
//import org.jetbrains.annotations.NotNull;
//
//import java.math.BigDecimal;
//
//import static java.math.BigDecimal.ROUND_HALF_EVEN;
//import static java.math.RoundingMode.HALF_UP;
//import static java.util.Objects.isNull;
//
///**
// * @author zmh
// */
//public class IntDecimal
//    extends Number
//    implements Comparable<IntDecimal>,
//    Cloneable,
//    SqlIntObject<IntDecimal> {
//    private static final int MULTIPLY_FACTORY = 100;
//    private static final BigDecimal BIGDEC_100 = BigDecimal.valueOf(MULTIPLY_FACTORY);
//
//    protected IntDecimal() {
//        this(0);
//    }
//
//    @Getter
//    private final long value;
//
//    public IntDecimal(@NotNull Integer v) {
//        this.value = v * MULTIPLY_FACTORY;
//    }
//
//    // require for SqlIntObject instance creation
//    public IntDecimal(long v, boolean rawValue) {
//        this.value = rawValue ? v : v * MULTIPLY_FACTORY;
//    }
//
//    public IntDecimal(double v) {
//        this.value = (long) (v * MULTIPLY_FACTORY);
//    }
//
//    // require for SqlIntObject instance creation
//    public IntDecimal(String str) {
//        this(new BigDecimal(str));
//    }
//
//    public IntDecimal(@NotNull BigDecimal v) {
//        this.value = bigDecimalToValue(v);
//    }
//
//    private int bigDecimalToValue(@NotNull BigDecimal v) {
//        return v.multiply(BIGDEC_100).setScale(0, HALF_UP).intValueExact();
//    }
//
//    public BigDecimal bigDecimalValue() {
//        return BigDecimal.valueOf(value).divide(BIGDEC_100, 2, ROUND_HALF_EVEN);
//    }
//
//    @Override
//    public int compareTo(@NotNull IntDecimal o) {
//        if (value == o.value) {
//            return 0;
//        }
//        return value > o.value ? 1 : -1;
//    }
//
//    public IntDecimal multiply(int v) {
//        return new IntDecimal(this.value * v, true);
//    }
//
//    @Override
//    public int intValue() {
//        return (int) (value / MULTIPLY_FACTORY);
//    }
//
//    @Override
//    public long longValue() {
//        return value / MULTIPLY_FACTORY;
//    }
//
//    @Override
//    public float floatValue() {
//        return value / 100.0f;
//    }
//
//    @Override
//    public double doubleValue() {
//        return value / 100.0d;
//    }
//
//    @Override
//    public String toString() {
//        return bigDecimalValue().toString();
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (isNull(obj)) {
//            return false;
//        }
//        if (obj instanceof IntDecimal) {
//            return compareTo((IntDecimal) obj) == 0;
//        }
//        if (obj instanceof BigDecimal) {
//            return value == bigDecimalToValue((BigDecimal) obj);
//        }
//        if (obj instanceof Number) {
//            return value == ((Number) obj).doubleValue();
//        }
//        return false;
//    }
//
//    public long rawValue() {
//        return this.value;
//    }
//
//    @Override
//    public Long getSqlValue() {
//        return this.value;
//    }
//
//    @Override
//    public IntDecimal deepCopy() {
//        return new IntDecimal(value, true);
//    }
//
//    @Override
//    protected Object clone() {
//        return deepCopy();
//    }
//
//}
