# DividerDecoration
DividerDecoration for RecyclerView
RecyclerView的分割线 装饰
可设置分割线颜色,高度
是否绘制最后一个分割线
是否绘制第一条列表项的顶部分割线

使用方法:

DividerDecoration itemDecoration = new DividerDecoration(getResources().getColor(R.color.list_divider_color), 0, 0, density * 8);
//itemDecoration.setDrawFirstDividerTop(true);//设置绘制第一个列表项顶部的分割线,并且高度和列表项的分割线高度相同
//使用给定的像素指高度设置第一个列表项顶部的分割线
itemDecoration.setDrawFirstDividerTop(true, 8 * density);
//设置不绘制最后一项的分割线
itemDecoration.setDrawLastDivider(false);
mRecyclerView.addItemDecoration(itemDecoration);


