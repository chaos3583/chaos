package com.chaos.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ControllerUtil {

	/**
	 * String类型的Json串转换为对象
	 * 
	 * @create 奕訢.lrg.2016-06
	 * 
	 * @param strJson
	 *            String类型的Json串
	 * @param toClass
	 *            目标对象类类型
	 * @return null || {object}
	 */
	public static <To> To strJsonToObject(String strJson, Class<To> toClass) {
		if (strJson.indexOf("{") == 0) {
			return JSON.parseObject(strJson, toClass);
		} else {
			return null;
		}
	}

	/**
	 * String类型的Json串转换为集合
	 * 
	 * @create 奕訢.lrg.2016-06
	 * 
	 * @param strJson
	 *            String类型的Json串
	 * @param toClass
	 *            目标集合类类型
	 * @return size()>=0的List
	 */
	public static <To> List<To> strJsonToArray(String strJson, Class<To> toClass) {
		List<To> result = new ArrayList<To>();
		if (strJson.indexOf("{") == 0) {
			result.add(JSON.parseObject(strJson, toClass));
		} else if (strJson.indexOf("[") == 0) {
			result = JSON.parseArray(strJson, toClass);
		}
		return result;
	}

	/**
	 * 复制对象（类型map的对象）数据
	 * 
	 * @create 奕訢.lrg.2016-06
	 * 
	 * @param source
	 *            {object}
	 * @param toClass
	 *            目标对象类类型
	 * @return null || {object}
	 */
	public static <S, To> To copyObject(S source, Class<To> toClass) {
		if (source == null) {
			return null;
		} else {
			return JSON.parseObject(JSON.toJSONString(source), toClass);
		}
	}

	/**
	 * 复制集合数据
	 * 
	 * @create 奕訢.lrg.2016-06
	 * 
	 * @param source
	 *            [list]
	 * @param toClass
	 *            目标集合类类型
	 * @return size()>=0的List
	 */
	public static <S, To> List<To> copyArray(List<S> source, Class<To> toClass) {
		if (source == null) {
			return new ArrayList<To>();
		} else {
			return JSON.parseArray(JSON.toJSONString(source), toClass);
//			List<To> resultList = JSON.parseArray(JSON.toJSONString(source), toClass);
//			if (CollectionUtils.isNotEmpty(resultList)) {
//				List<Field> decimalTypeList = Lists.newArrayList();
//				Field[] fields = resultList.get(0).getClass().getDeclaredFields();
//				for (Field field : fields) {
//					if (field.getType().getName().equals("java.math.BigDecimal")) {
//						decimalTypeList.add(field);
//					}
//				}
//
//				//yanlin 20200220 decimal类型的数据值保留到最后一位有效位
//				if (CollectionUtils.isNotEmpty(decimalTypeList)) {
//					try {
//						for (Field field : decimalTypeList) {
//							field.setAccessible(true);
//							for (To to : resultList) {
//								BigDecimal fieldValue = new BigDecimal(field.get(to).toString());
//								BigDecimal plainValue = new BigDecimal(fieldValue.stripTrailingZeros().toPlainString());
//								field.set(to, plainValue);
//							}
//						}
//					} catch (Exception ex) {}
//				}
//
//			}
//			return resultList;
		}
	}

	/**
	 * 
	 * @create 奕訢.lrg.2016-20
	 *
	 * @param <P1>
	 * @param <P2>
	 * @param <P3>
	 */
	@FunctionalInterface
	public interface FnBoolean<P1,P2,P3> {
		public  Boolean doMethod(P1 p1, P2 p2, P3 p3);
	}
	/**
	 * 
	 * @create 奕訢.lrg.2016-20
	 *
	 * @param <R>
	 * @param <P>
	 */
	@FunctionalInterface
	public interface FnServiceResult<R,P> {
		public ServiceResult<R> doMethod(P param);
	}

	/**
	 * SQL关键字校验，防注入
	 * @param str
	 * @return
	 */
	public static boolean sqlValidate(String str) {
		if (StringUtils.isEmpty(str)) {
			return false;
		}

		//统一转为小写
		str = str.toLowerCase();
		//过滤掉的sql关键字，可以手动添加
		String badStr = "'| and |exec|execute|insert |select |delete |update |chr|mid|master|truncate|char|declare"
				+ "|net user|xp_cmdshell|like'|create |drop |table| from |grant|use|group_concat|column_name"
				+ "|information_schema.columns|table_schema|union|where| by |count|*| or |;|-|--|+|like|//|/|%|#";
		String[] badStrs = badStr.split("\\|");
		for (int i = 0; i < badStrs.length; i++) {
			//循环检测，判断在请求参数当中是否包含SQL关键字
			if (str.indexOf(badStrs[i]) >= 0) {
				return true;
			}
		}
		return false;
	}

}
