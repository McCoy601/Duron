//index.js


Page({
  data: {
    current: 1,
    canNext: false,
    canPrevious: true
  },
  index: 0,
  cacheItems: [],
  itemCount: 0,
  onLoad: function (option) {
    const _this = this;
    wx.showLoading({
      title: '一大波毒鸡汤正在靠近',
    });
    wx.request({
      url: 'https://duron.xiangduhui.com/duron',
      success: function (res) {
        if (res.data) {
          wx.setStorageSync("CALENDAR_ITEMS", res.data);
          _this.cacheItems = res.data;
          _this.itemCount = _this.cacheItems.length;
          _this.index = _this.cacheItems.length - 1;
          _this.setData({
            calendar: _this.cacheItems.slice(_this.index - 1)
          })
        }
      },
      fail: function (err) {
        let _items = wx.getStorageSync("CALENDAR_ITEMS");
        _items = _items || [];
       _items= _items.concat([{
          "id": _now+"",
          "era": "网络异常，请稍后重试",
          "lunar": "春风吹了不一定能生",
          "week": "星期八",
          "year": new Date().getFullYear(),
          "mouth": 2,
          "day": 31,
          "act": "等待",
          "taboo": false,
          "motto": "服务器中毒身亡"
        }]);

        _this.cacheItems = _items;
        _this.itemCount = _this.cacheItems.length;
        _this.index = _this.cacheItems.length - 1;
        _this.setData({
          calendar: _this.cacheItems.slice(_this.index - 1)
        })
      },
      complete: function () {
        wx.hideLoading();
      }
    })
  },
  onReady: function () {

    // this.setData({
    //   currentIndex:1
    // });
  },
  next: function () {//下一天
    const _this = this;

    if (_this.index >= _this.itemCount - 1) {
      wx.showToast({
        title: '未来,已来'
      });

      return;
    }

    _this.index += 1;

    let _data = {
      current: _this.index == 1 ? 1 : 2,
      canPrevious: _this.index > 0,
      canNext: _this.index < _this.itemCount - 1
    }

    _this.setData(_data);

    _this._renderSwiper();
  },
  previous: function () {//前一天
    const _this = this;
    if (_this.index <= 0) {
      wx.showToast({
        title: '滚滚长江东逝水'
      });
      return;
    }

    _this.index -= 1;
    let _data = {
      current: 0,
      canPrevious: _this.index > 0,
      canNext: _this.index < _this.itemCount - 1
    };
    _this.setData(_data);//swiper前滑
    _this._renderSwiper();
  },
  onSwiperChange: function (e) {//swiper change事件
    if (e.detail.source != 'touch') {
      return;
    }
    const _this = this;
    if (e.detail.current == 0) {
      _this.previous();
    } else if (e.detail.current == 2 || e.detail.current == 1) {
      _this.next();
    }
  },
  _renderSwiper: function () {//渲染swiper item
    let items = null;
    const _this = this;
    let begin = _this.index - 1;
    let end = begin + 3;

    if (end >= _this.itemCount) {
      items = _this.cacheItems.slice(begin);

    } else if (begin < 0) {
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