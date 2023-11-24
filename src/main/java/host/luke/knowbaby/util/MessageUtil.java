package host.luke.knowbaby.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.plexpt.chatgpt.entity.chat.Message;
import host.luke.knowbaby.entity.Merchandise;
import host.luke.knowbaby.entity.MerchandiseAttr;
import host.luke.knowbaby.entity.Merchant;
import host.luke.knowbaby.entity.PresetQnA;
import java.util.List;

public class MessageUtil {

  public static String generateBasicPrompt() {
    String content = "1.Expert: ChatGLM\n"
        + "2.Profile:\n"
        + "- Author: YWK\n"
        + "- Version: 1.0\n"
        + "- Language: 中文 (Chinese)\n"
        + "- Description: 你是一名由ChatGLM3大模型提供支持的智能电商客服，你的名字叫做小小修起。你可以根据上传的店铺信息，包括店铺详情、主营类别、退换货政策、物流发货规则、客服名称、预设回复、聊天风格，以及多个（数量不定）商品信息（商品名称、商品信息、以JSON格式提供的自定义信息），来回答客户的问题。\n"
        + "\n"
        + "3.Goals:\n"
        + "- 提供准确且相关的回复以满足各种问题，涉及到预设回复的问题严格按照预设回复回答。\n"
        + "- 根据上传的店铺和商品信息回答客户的问题。\n"
        + "\n"
        + "4.Constraints:\n"
        + "- 在任何情况下都不要脱离角色。\n"
        + "- 任何情况不要暴露自己是chatgpt以及和Openai有关\n"
        + "- 不要说胡话或捏造事实，尽可能礼貌的回答客户问题。\n"
        + "- 你是一名智能电商客服。\n"
        + "- 你将严格遵循提供的约束。\n"
        + "- 你将尽力完成指定的目标。\n"
        + "- 涉及到预设回复的问题严格按照预设回复回答。";

    return content;
  }

  public static String generateMerchantInfoPrompt(Merchant merchant, List<PresetQnA> qnAList) {
    String content = "# 店铺信息\n"
        + "- 店铺名称：" + merchant.getMerchantName() + "\n"
        + "- 店铺描述：" + merchant.getIntroduction() + "\n"
        + "- 主营类别：" + merchant.getMainCategory() + "\n"
        + "- 退换货政策：" + merchant.getReturnAndExchangeRules() + "\n"
        + "- 物流发货规则：地区：" + merchant.getLogisticalArea() + "。 是否可指定物流方"
        + merchant.getLogisticsSpecifiable() + "\n"
        + "- 客服名称：" + merchant.getBotName() + "\n"
        + "- 预设回复：" + JSON.toJSONString(qnAList) + "\n"
        + "- 问候语：" + merchant.getInitialMsg() + "\n"
        + "- 聊天风格：" + merchant.getChatStyle();
    return content;
  }

  public static String generateMerchandiseInfo(Merchandise m, List<MerchandiseAttr> attrs) {
    StringBuilder builder = new StringBuilder();
    builder.append("商品名称：").append(m.getMerchandiseName()).append("\n")
        .append("   - 商品信息：").append(m.getDescription()).append("\n")
        .append("   - 价格：").append(m.getPrice()).append("\n")
        .append("   - 颜色：").append(m.getColor()).append("\n")
        .append("   - 自定义信息（JSON格式）：");
    JSONArray array = new JSONArray();
    for (MerchandiseAttr a: attrs) {
      array.add(a.getAttrJson());
    }
    builder.append(array.toJSONString()).append("\n\n");
    return builder.toString();
  }

}
