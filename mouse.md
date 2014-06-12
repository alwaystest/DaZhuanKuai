#java获取鼠标指针位置

Point  component.getMousePosition()  

如果此 Component 正好位于鼠标指针下，则返回鼠标指针在该 Component 的坐标空间中的位置；否则返回 null。 返回值是Point对象，**需要用  Point.x  获取到其横座标**  
component不在鼠标位置下方会报错，打砖块中设置了mouseEntered和mouseExited监听器判断鼠标  

添加Listener或者Adapter

    this.addMouseListener(new MouseAdapter(){
			public void mouseMoved(MouseEvent e){
				//MyWall.setlocation(e.getX());
				System.out.println(e.getX());
			}//需要加载到正确的component上
  
###以上的代码没有达到应有的效果
正确的代码如下  

    .addMouseMotionListener(new MouseAdapter(){
			public void mouseMoved(MouseEvent e){
				MyWall.setlocation(e.getX());
				//System.out.println(e.getX());
			}//没有效果，好象是没有加载到正确的component上
			public void mouseEntered(MouseEvent e){
				mp=true;
				System.out.println("entered");
			}
			public void mouseExited(MouseEvent e){
				mp=false;
				System.out.println("exited");
				}
		});
  
###因为mouseMotionListener才能监听到鼠标的移动动作
  

Point mousepoint = MouseInfo.getPointerInfo().getLocation()
获取到的鼠标位置是相对**显示器座标系**的坐标
          
