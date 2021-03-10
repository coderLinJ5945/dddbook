//package com.dddbook.bank.types;
//
//import com.sun.istack.internal.NotNull;
//import lombok.NoArgsConstructor;
//import lombok.Value;
//import org.apache.commons.lang3.Validate;
//import java.util.EnumSet;
//import static java.lang.String.format;
//import static java.util.Objects.isNull;
//import static java.util.Objects.nonNull;
//import static lombok.AccessLevel.PRIVATE;
//import static org.apache.commons.lang3.StringUtils.isEmpty;
//
///**
// * @author zmh
// */
//@Value
//@With
//@NoArgsConstructor(access = PRIVATE, force = true)
//@FieldsValueNotAllNull(fields = {"houseId", "roomId"})
//public class HouseRoomCombineId {
//    @NotNull
//    private BizType bizType; // only apt or room
//    private Long houseId;
//    private Long roomId;
//
//    public HouseRoomCombineId(
//        @javax.validation.constraints.NotNull BizType bizType,
//        Long houseId,
//        Long roomId
//    ) {
//        if (BizType.ROOM.equals(bizType)) {
//            houseId = null;
//        } else {
//            bizType = APT;
//            roomId = null;
//        }
//        Validate.isTrue(nonNull(houseId) || nonNull(roomId));
//
//        this.bizType = bizType;
//        this.houseId = houseId;
//        this.roomId = roomId;
//    }
//
//    private String stringOrEmpty(Object o) {
//        return isNull(o) ? "" : o.toString();
//    }
//
//    public String toStringId() {
//        String t = isNull(bizType) ? "" : String.valueOf(bizType.getId());
//        return format("%s:%s:%s", stringOrEmpty(houseId), t, stringOrEmpty(roomId));
//    }
//
//    public static String toStringId(
//        BizType bizType,
//        Long houseId,
//        Long roomId
//    ) {
//        return new HouseRoomCombineId(bizType, houseId, roomId).toStringId();
//    }
//
//    public static String fromBldId(Long aptId) {
//        return toStringId(BizType.BLD, aptId, null);
//    }
//
//    public static String fromHouseId(Long houseId) {
//        return toStringId(APT, houseId, null);
//    }
//
//    public static String fromRoomId(Long roomId) {
//        return toStringId(BizType.ROOM, null, roomId);
//    }
//
//    public static String toStringId(
//        Integer bizType,
//        Long houseId,
//        Long roomId
//    ) {
//        return new HouseRoomCombineId(BizType.getById(bizType), houseId, roomId).toStringId();
//    }
//
//    public static HouseRoomCombineId fromString(String str) {
//        if (isEmpty(str)) {
//            return null;
//        }
//        if (str.startsWith(":")) {
//            str = "0" + str;
//        }
//        if (str.endsWith(":")) {
//            str += "0";
//        }
//        String[] split = str.split(":");
//        if (split.length < 3) {
//            throw new InvalidStateException("NOT a HouseRoomCombinedId:" + str);
//        }
//
//        Integer id = Integer.valueOf(split[1]);
//        BizType bizType = EnumSet.allOf(BizType.class)
//            .stream()
//            .filter(e -> e.getId() == id)
//            .findFirst()
//            .orElse(null);
//        return new HouseRoomCombineId(
//            bizType,
//            Long.valueOf(split[0]),
//            Long.valueOf(split[2])
//        );
//    }
//
//
//    @Override
//    public String toString() {
//        return toStringId();
//    }
//}
