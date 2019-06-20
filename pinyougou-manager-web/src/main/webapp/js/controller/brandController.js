var app = new Vue({
    el: "#app",
    data: {
        brandList: [],
        entity: {},
        ids: [],
        searchEntity: {},
        pages: 15,//总页数
        pageNo: 1//当前页码
    },
    methods: {
        dele: function () {
            axios.post('/brand/delete.do', this.ids).then(function (response) {
                console.log(response);
                if (response.data.success) {
                    app.ids=[];//清空
                    app.searchList(1);
                }
            }).catch(function (error) {
                console.log("1231312131321");
            });
        },
        save: function () {
            if (this.entity.id != null) {
                this.update();
            } else {
                this.add();
            }
        },
        update: function () {
            axios.post('/brand/update.do', this.entity).then(function (response) {
                console.log(response);
                if (response.data.success) {
                    app.searchList(1);
                }
            }).catch(function (error) {
                console.log("1231312131321");
            });
        },
        findOne: function (id) {
            axios.get('/brand/findOne/' + id + '.do').then(function (response) {
                app.entity = response.data;
            }).catch(function (error) {
                console.log("1231312131321");
            });
        },
        add: function () {
            axios.post('/brand/add.do', this.entity).then(function (response) {
                console.log(response);
                if (response.data.success) {
                    app.searchList(1);
                }
            }).catch(function (error) {
                console.log("1231312131321");
            });
        },
        searchList: function (curPage) {
            axios.post('/brand/search.do?pageNo=' + curPage, this.searchEntity).then(function (response) {
                //获取数据
                app.brandList = response.data.list;

                //当前页
                app.pageNo = curPage;
                //总页数
                app.pages = response.data.pages;
            });
        },
        //查询所有品牌列表
        findAll: function () {
            console.log(app);
            axios.get("/brand/findAll.do").then(function (response) {
                console.log(response);
                app.brandList = response.data;
            }).catch(function (error) {
                console.log(error)
            })
        }
    },
    created: function () {
        // this.findAll();
        this.searchList(1);
    }
});