//index.js


Page({
  data: {
    current: 1,
    canNext: false,
    canPrevious: true
  },
  index: -1,
  cacheItems: [],
  itemCount: 0,
  onLoad: function (option) {
    const _this = this;
    for (let i = 0; i < 10; i++) {
      let tempDate = new Date(new Date().setDate(i - 19));
      _this.cacheItems.push({
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
    _this.itemCount = _this.cacheItems.length;

    _this.setData({
      calendar: _this.cacheItems.slice(_this.index - 1)
    })
  },
  onReady: function () {

    // this.setData({
    //   currentIndex:1
    // });
  },
  next: function () {//下一天
    const _this = this;

    if (_this.index >= -1) {
      wx.showToast({
        title: '未来,已来'
      });

      return;
    }

    let _data = {
      current: _this.index == -_this.itemCount ? 1 : 2,
      canPrevious: _this.index >= -_this.itemCount,
      canNext: _this.index < -2
    }

    _this.setData(_data);

    _this.index += 1;
    _this._renderSwiper();
  },
  previous: function () {//前一天
    const _this = this;
    let _data = {
      current: 0,
      canPrevious: _this.index > -_this.itemCount,
      canNext: _this.index <= -1
    };
    _this.setData(_data);//swiper前滑
    if (_this.index <= -_this.itemCount) {
      return;
    }
    _this.index -= 1;
    _this._renderSwiper();
  },
  onSwiperChange: function (e) {//swiper change事件
    if (e.detail.source != 'touch') {
      return;
    }
    const _this = this;
    if (e.detail.current == 0) {
      _this.previous();
    } else if (e.detail.current == 2) {
      _this.next();
    }
  },
  _renderSwiper: function () {//渲染swiper item
    let items = null;
    const _this = this;
    let begin = _this.index - 1;
    let end = begin + 3;

    if (end >= -1) {
      items = _this.cacheItems.slice(begin);

    } else if (begin < -_this.itemCount) {
      //数据不够
    } else {
      items = _this.cacheItems.slice(begin, end);
    }

    if (items) {
      setTimeout(function () {
        _this.setData({
          calendar: items,
          current: 1
        });
      }, 500);
    }
  }
})