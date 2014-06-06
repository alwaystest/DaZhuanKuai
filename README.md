



#开 发 文 档

###本游戏基于JAVA开发，实现打砖块的实现。

Zhuankuai0.8
说明:
1.修改了GAME.java中砖块的生成方法。并已经以循环方式写出4行砖块。

新增BUG：
1.生成砖块后小球未能与砖块进行碰撞，我认为是某处少了add()。引眼睛受不了了，明天再搞。
2.当小球与砖块对角相撞时可能出现原路返回，检验请按照ZHUANKUAI0.7中第一个砖块检验。(这个不算bug吧，我设置成这样子了，按照物理原理，应该是往回走吧，要么应该往哪里走)
时间2014/2/7

Bug修复：
Bug1是你没有弄明白判断碰撞是在哪里，你看看14.2.8那里，是判断碰撞的。
Bug2的确有缺陷，本来是碰到角以后原路返回，现在验证是判断误差较大，碰到角附近也会原路返回，这个算法要么自己想，要么去网上找源码，实在不行去淘宝买，会有人能想出来的。自己想太耗时了，有点锻炼数学能力。
14.2.8

Zhuankuai0.7:
说明：
新加入一行砖块测试。找出常用测试位置。已在代码中说明。准备修复0.6  bug0。

Bug1：wall.java
wall移动时的bug，原先没有引入width参数，改变width后会出错		已修复
Wall移动没有bug后设置其最长，方便测试。

ZK.java
此版本由于判断碰撞的逻辑不完善，砖块大小不能太小，否则会出错。

Ball.java
修改判断碰撞用的生成矩形的方法，使其更合理。判断碰撞方向的方法想了半天，晚上写     14.1.28
暂时写完判断碰撞，ball的步进不能太大，否则判断出错。待测试其余bug。   14.1.31

GAME.java  砖块生成问题已完善
需要重写多个砖块的生成方法，可以借鉴子弹的写法。一个一个写太累，画出砖块的循环那里砖块个数交给函数。

Zhuankuai0.6:
加入了一个砖块，小球碰到砖块可以反弹。利用当前球与砖块的位置生成矩形对象，利用判断矩形对象是否相交的方法确定小球与砖块的碰撞。
板子移动流畅,正常。
目前版本bug0：小球只能从上部击打砖块，判断逻辑需要改进。
微调：
1.小球触碰边缘，边缘判断需要改进。
2.几处常用到的数据需要提取出来，单独设置变量，方便以后调试。
2014.01.26

Zhuankuai0.5:
板子移动已经改好，板子移动顺畅，可以降低sleep()时间，改小移动距离，已改动
此版本让小球碰到板子反弹 ，wall接不到小球，小球就死亡，游戏结束；
2014.01.26完成。

Zhuankuai0.4:
试图解决板子移动第一下慢的问题。
板子出现问题只向左移动再向右移动，然后无法移动。
2014.01.2完成。

Zhuankuai0.3:
加入Ball.java，使小球在游戏窗口中弹跳起来。
2014.1.24完成。
		
Zhuankuai0.2：
将Zhuangkuai.java与Wall类分离，并将板子限定在游戏背景范围内移动。
2014.1.23完成。

Zhuankuai0.1：
创立游戏窗口，以直线当做板子，可左右移动。只有Zhuankuai.java。
2014.1.22完成。