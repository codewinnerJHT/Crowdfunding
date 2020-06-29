package com.example.haitao.crowdfunding.bean;

import java.util.List;

/**
 * Created by haitao on 2020/6/25.
 */

public class NewsInfoBean {

    /**
     * msg : 请求成功
     * news : [{"comment":1456,"contentone":"6月25日，软银CEO孙正义在年度股东大会上表示，将退出阿里巴巴董事会，自今日生效。同日，马云辞去13年软银董事一职，双方离场或象征着二人长达二十年的合作就此分手。","contentthree":"去年，软银因为旗下愿景基金投资不善，全年亏损达1.36万亿日元。在此之下，阿里再次变身\u201c现金奶牛\u201d，据此前彭博社报道，孙正义拟在今年3月从阿里套现价值140亿美元的股票，以弥补软银当前因投资导致的各项亏损","contenttwo":"早年间，孙正义6分钟投资马云的故事还在外界流传。可以说到现在，阿里依旧是软银最有价值的资产，孙正义表示，软银将尽可能长期持有阿里巴巴股票","id":1,"imageurlone":"/1443527992618.jpg","imageurlthree":"/1447999535316.jpg","imageurltwo":"/1442219020234.jpg","publisher":" \r\nAI财经社","time":"2020-06-25 11:38:23","title":" \r\n孙正义退出阿里巴巴董事会，马云同日卸任软银董事","type":1},{"comment":163,"contentone":"被冷落的大众车，22块钱能跑278公里，60天销29台，为何没人买？\r\n\r\n新能源汽车是最近这些年来车市的热门话题，从认识到接受新能源车的消费者是越来越多，纵使目前新能源汽车，尤其是纯电动汽车，它依然有种种不完美。2019年，全球新能源汽车销量依旧增长10%至220万辆，其中纯电动车是销量主力，占据了74%的市场份额。国内方面，2019年则因为补贴退坡以及车市本身不景气影响，新能源汽车销量出现了近10年来的首次下降，销120.6万辆跌4%。","contentthree":"作为最大的汽车集团，大众自然不愿在新能源汽车时代落后对手，而中国作为大众汽车全球最大的单一市场，其重要性是不言而喻的。朗逸则是大众在国内的\u201c绝对王牌\u201d车型，2019年销量高达526291辆，应该说具有很好的群众基础和认可度，在朗逸的基础上推出一款纯电动车变成了顺理成章的事情。","contenttwo":"只是，随着国产特斯拉的到来，似乎大家对新能源汽车的热情再次被点燃，特斯拉MODEL 3作为全球最受欢迎的纯电动汽车，2019年全球销量300075辆。国产后，今年2月销量高达3900辆，位列轿车销量第三名，仅次于轩逸和宝来。当然，2月因为受到疫情影响，汽车销售大受影响，当汽油车型回归正常销量后，MODEL 3的排名自然会下降，2月3900辆的销量却证明了，新能源汽车在国内的接受和认可程度是越来越高的，随着续航里程、充电便捷度的逐渐提升，未来新能源汽车依然会是一大消费趋势。\r\n\r\n","id":2,"imageurlone":"/1457323096680.jpg","imageurlthree":"/1458044708337.jpg","imageurltwo":"/1457504361484.jpg","publisher":"购车网","time":"2020-03-31 17:22:44","title":"不用烧油的朗逸，22块钱能跑278km，60天才卖29台，为何没人买？","type":1}]
     * code : 200
     */

    private String msg;
    private String code;
    private List<NewsBean> news;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<NewsBean> getNews() {
        return news;
    }

    public void setNews(List<NewsBean> news) {
        this.news = news;
    }

    public static class NewsBean {
        /**
         * comment : 1456
         * contentone : 6月25日，软银CEO孙正义在年度股东大会上表示，将退出阿里巴巴董事会，自今日生效。同日，马云辞去13年软银董事一职，双方离场或象征着二人长达二十年的合作就此分手。
         * contentthree : 去年，软银因为旗下愿景基金投资不善，全年亏损达1.36万亿日元。在此之下，阿里再次变身“现金奶牛”，据此前彭博社报道，孙正义拟在今年3月从阿里套现价值140亿美元的股票，以弥补软银当前因投资导致的各项亏损
         * contenttwo : 早年间，孙正义6分钟投资马云的故事还在外界流传。可以说到现在，阿里依旧是软银最有价值的资产，孙正义表示，软银将尽可能长期持有阿里巴巴股票
         * id : 1
         * imageurlone : /1443527992618.jpg
         * imageurlthree : /1447999535316.jpg
         * imageurltwo : /1442219020234.jpg
         * publisher :
         AI财经社
         * time : 2020-06-25 11:38:23
         * title :
         孙正义退出阿里巴巴董事会，马云同日卸任软银董事
         * type : 1
         */

        private int comment;
        private String contentone;
        private String contentthree;
        private String contenttwo;
        private int id;
        private String imageurlone;
        private String imageurlthree;
        private String imageurltwo;
        private String publisher;
        private String time;
        private String title;
        private int type;

        public int getComment() {
            return comment;
        }

        public void setComment(int comment) {
            this.comment = comment;
        }

        public String getContentone() {
            return contentone;
        }

        public void setContentone(String contentone) {
            this.contentone = contentone;
        }

        public String getContentthree() {
            return contentthree;
        }

        public void setContentthree(String contentthree) {
            this.contentthree = contentthree;
        }

        public String getContenttwo() {
            return contenttwo;
        }

        public void setContenttwo(String contenttwo) {
            this.contenttwo = contenttwo;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImageurlone() {
            return imageurlone;
        }

        public void setImageurlone(String imageurlone) {
            this.imageurlone = imageurlone;
        }

        public String getImageurlthree() {
            return imageurlthree;
        }

        public void setImageurlthree(String imageurlthree) {
            this.imageurlthree = imageurlthree;
        }

        public String getImageurltwo() {
            return imageurltwo;
        }

        public void setImageurltwo(String imageurltwo) {
            this.imageurltwo = imageurltwo;
        }

        public String getPublisher() {
            return publisher;
        }

        public void setPublisher(String publisher) {
            this.publisher = publisher;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
