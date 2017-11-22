//index.js


Page({
  data: {
    current: 1
  },
  index: -1,
  testList: [],
  onLoad: function (option) {
    const _this=this;
    for (let i = 0; i < 20; i++) {
      let tempDate = new Date(new Date().setDate(i - 19));
      _this.testList.push({
        id: tempDate.toLocaleDateString(),
        era: '丙申猴年 辛丑月 庚子日',
        lunar: '腊月十六',
        week: `星期${tempDate.getDay()}`,
        year: tempDate.getFullYear(),
        mouth: tempDate.getMonth() + 1,
        day: tempDate.getDate(),
        act: '混吃等死',
        taboo: i % 2 == 0,//宜忌
        motto: tempDate.toLocaleDateString()
      })
    }

    _this.setData({
      calendar: _this.testList.slice(_this.index - 1)
    })
  },
  onReady: function () {

    // this.setData({
    //   currentIndex:1
    // });
  },
  next: function () {
    const _this=this;

    if (_this.index >= -1) {
      wx.showToast({
        title: '未来,已来'
      });
      return;
    }
    _this.index += 1;
    _this._renderSwiper(_this.index - 1, _this.index + 2);
  },
  previous: function () {
    const _this=this;
    _this.index -= 1;
    _this._renderSwiper(_this.index - 1, _this.index + 2);
  },
  onSwiperChange: function (e) {
    const _this=this;
    if (e.detail.current == 0) {
      _this.previous();
    } else if (e.detail.current == 2) {
      _this.next();
    }
  },
  _renderSwiper: function (begin, end) {
    let items = null;
    const _this=this;
    if (end >= -1) {
      items = _this.testList.slice(begin);
    } else if (begin < -_this.testList.length) {
      //数据不够
    } else {
      items = _this.testList.slice(begin, end);
    }

    if (items) {
      setTimeout(function(){
        _this.setData({
          calendar: items,
          current: 1
        });
      },500);
    }
  }
})