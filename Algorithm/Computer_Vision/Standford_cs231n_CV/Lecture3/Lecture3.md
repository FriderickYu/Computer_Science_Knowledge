# Loss Functions and Optimization

## 什么是损失函数, 它是干什么用的
书接上回的`linear classifier`, 当使用其计算出每个图片在各个分类的对应分数时, 有可能会发生问题

<img alt="简单的计算" height="411" src="../images/Lecture3/simple_scores.png" width="500"/>

即分数最高代表的预测结果和实际的分类可能是不一样, 而这个时候我们想到的必然是调整权重$W$

但如何选取一个合适的$W$从而得到满意的预测结果, 这就需要`loss function`, 即*损失函数*来对$W$进行修正

损失函数以$W$为输入, 最终输出一个指标, 而这个指标用来衡量$W$是好, 还是坏?

而所谓的*优化*, 其实就是从一群$W$中挑选一个不太坏的$W$