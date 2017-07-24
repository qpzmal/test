package cn.advu.workflow.dao.fcf_vu.datareport;

import cn.advu.workflow.dao.base.ISqlMapper;
import cn.advu.workflow.domain.fcf_vu.datareport.VuDataReport;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 报表Mapper
 */
public interface VuDataReportMapper extends ISqlMapper {
    /**
     * 1.	销售报表分析系统
     * 1)	历史订单额汇总
     * a)	按照时间维度汇总的订单数据，可自由设定起止时间。默认生成月，季度和半年和年订单额汇总数。 自动生成
     * 历史订单--默认：
     * @param startDate
     * @param endDate
     * @return
     */
    List<VuDataReport> querySaleHistoryByDate(@Param("startDate") String startDate, @Param("endDate") String endDate);
    /**
     * 1.	销售报表分析系统
     * 1)	历史订单额汇总
     * a)	按照时间维度汇总的订单数据，可自由设定起止时间。默认生成月，季度和半年和年订单额汇总数。 自动生成
     * 历史订单--月：
     * @param startDate
     * @param endDate
     * @return
     */
    List<VuDataReport> querySaleHistoryByDateMonth(@Param("startDate") String startDate, @Param("endDate") String endDate);
    /**
     * 1.	销售报表分析系统
     * 1)	历史订单额汇总
     * a)	按照时间维度汇总的订单数据，可自由设定起止时间。默认生成月，季度和半年和年订单额汇总数。 自动生成
     * 历史订单--季度：
     * @param startDate
     * @param endDate
     * @return
     */
    List<VuDataReport> querySaleHistoryByDateQuarter(@Param("startDate") String startDate, @Param("endDate") String endDate);
    /**
     * 1.	销售报表分析系统
     * 1)	历史订单额汇总
     * a)	按照时间维度汇总的订单数据，可自由设定起止时间。默认生成月，季度和半年和年订单额汇总数。 自动生成
     * 历史订单--半年：
     * @param startDate
     * @param endDate
     * @return
     */
    List<VuDataReport> querySaleHistoryByDateHalfYear(@Param("startDate") String startDate, @Param("endDate") String endDate);
    /**
     * 1.	销售报表分析系统
     * 1)	历史订单额汇总
     * a)	按照时间维度汇总的订单数据，可自由设定起止时间。默认生成月，季度和半年和年订单额汇总数。 自动生成
     * 历史订单--年：
     * @param startDate
     * @param endDate
     * @return
     */
    List<VuDataReport> querySaleHistoryByDateWholeYear(@Param("startDate") String startDate, @Param("endDate") String endDate);
    /**
     * 1.	销售报表分析系统
     * 1)	历史订单额汇总
     * b)	按照地域维度汇总的销售报告，例如北京，上海，广州等分公司，以及各地经销商。自动生成
     * @return
     */
    List<VuDataReport> querySaleHistoryByArea();
    /**
     * 1.	销售报表分析系统
     * 1)	历史订单额汇总
     * c)	按照销售负责人汇总的报告，每月，每季度，每年列出销售英雄榜，按照销售额排列。自动生成
     * @param startDate
     * @param endDate
     * @return
     */
    List<VuDataReport> querySaleHistoryBySaler(@Param("startDate") String startDate, @Param("endDate") String endDate);
    /**
     * 1.	销售报表分析系统
     * 1)	历史订单额汇总
     * c)	按照销售负责人汇总的报告，每月，每季度，每年列出销售英雄榜，按照销售额排列。自动生成
     * 月
     * @param startDate
     * @param endDate
     * @return
     */
    List<VuDataReport> querySaleHistoryBySalerMonth(@Param("startDate") String startDate, @Param("endDate") String endDate);
    /**
     * 1.	销售报表分析系统
     * 1)	历史订单额汇总
     * c)	按照销售负责人汇总的报告，每月，每季度，每年列出销售英雄榜，按照销售额排列。自动生成
     * 季度
     * @param startDate
     * @param endDate
     * @return
     */
    List<VuDataReport> querySaleHistoryBySalerQuarter(@Param("startDate") String startDate, @Param("endDate") String endDate);
    /**
     * 1.	销售报表分析系统
     * 1)	历史订单额汇总
     * c)	按照销售负责人汇总的报告，每月，每季度，每年列出销售英雄榜，按照销售额排列。自动生成
     * 年
     * @param startDate
     * @param endDate
     * @return
     */
    List<VuDataReport> querySaleHistoryBySalerWholeYear(@Param("startDate") String startDate, @Param("endDate") String endDate);
    /**
     * 1.	销售报表分析系统
     * 1)	历史订单额汇总
     * d)	按照4A公司或直客汇总的报告，可自由选择任意起止时间，指定4A公司或直客的汇总。自动生成
     * @param startDate
     * @param endDate
     * @return
     */
    List<VuDataReport> querySaleHistoryByCustomType(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("customType") String customType);

    /**
     * 1.	销售报表分析系统
     * 2)	未来订单额情况汇总：（客户需求单）
     * a)	已经签订排期的订单额汇总报告，可按照地域和时间分别查询。自动生成
     * @param startDate
     * @param endDate
     * @return
     */
    List<VuDataReport> querySaleFeature(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("queryType") String queryType);


    /**
     * 2.	资源采购分析系统
     * @return
     */




    /**
     * 3.	分公司财务分析系统
     * 2)	各分公司季度预算，半年预算和全年预算完成度分析，当期各分公司实际完成数（历史已经完成订单+已经有排期的未来订单）/ 当期预算数=当期预算完成百分比。财务填写基础数据而后自动生成
     * 预算完成度分析--半年预算
     * @param startDate
     * @param endDate
     * @return
     */
    List<VuDataReport> queryAreaBudgetByDateHalfYear(@Param("startDate") String startDate, @Param("endDate") String endDate);
    /**
     * 3.	分公司财务分析系统
     * 2)	各分公司季度预算，半年预算和全年预算完成度分析，当期各分公司实际完成数（历史已经完成订单+已经有排期的未来订单）/ 当期预算数=当期预算完成百分比。财务填写基础数据而后自动生成
     * 预算完成度分析--全年预算
     * @param startDate
     * @param endDate
     * @return
     */
    List<VuDataReport> queryAreaBudgetByDateWholeYear(@Param("startDate") String startDate, @Param("endDate") String endDate);










    /**
     * 4.	分公司财务分析系统
     * 1)	计算每个4A或者直客以及每个代理商的毛利贡献率和净利润贡献率，可自由设定起止时间。默认生成月，季度和半年和全年。净利润贡献率=净收入（订单额-对公返点）-分摊成本（加权平均计算）-其他综合分摊费用（包括工资，房租，销售提成和其他返点等）。毛利贡献率=净收入（订单额-对公返点）-分摊成本(加权平均计算)。财务填写基础数据而后自动生成
     * 毛利/净利贡献率
     * @return
     */
    List<VuDataReport> queryCustomerByProfit(@Param("startDate") String startDate, @Param("endDate") String endDate);
    /**
     * 4.	分公司财务分析系统
     * 1)	计算每个4A或者直客以及每个代理商的毛利贡献率和净利润贡献率，可自由设定起止时间。默认生成月，季度和半年和全年。净利润贡献率=净收入（订单额-对公返点）-分摊成本（加权平均计算）-其他综合分摊费用（包括工资，房租，销售提成和其他返点等）。毛利贡献率=净收入（订单额-对公返点）-分摊成本(加权平均计算)。财务填写基础数据而后自动生成
     * 回款汇总
     * @return
     */
    List<VuDataReport> queryCustomerByPay(@Param("startDate") String startDate, @Param("endDate") String endDate);
}