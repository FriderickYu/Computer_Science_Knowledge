# Image Classification

## 什么叫分类算法
分类任务: 给出一张图片->给出对应的标签

面临的问题:
* 计算机看到的具体来说不是一张图片, 而是一个像素矩阵(比如说800*600*3), *如何把图片和其像素矩阵联系起来是个问题*
* Viewpoint variation, 移动相机, 图片的角度会有所改变, 矩阵中的像素点会随之改变
* Illumination
* Deformation, 图片中的物体会有不同程度的形变, 但本质上还是一个物体, *比如猫*
* Occlusion, 遮挡
* etc....



## Data-Driven
视觉算法不能像排序算法一样硬编码, 没有一个标准的处理流程, 所以把数据丢给计算机, 让计算机自己去学习是最合适的一种选择
* 寻找图片集
* 人工分类 -> 数据标注
* 丢给机器 -> 训练数据集 => 找出合适的`classifier`
* 用训练好的`classifier`去预测新的图片

### 最简单的classififer, Nearest Neighbor
`train`: 读取并存储所有`data`和`labels`
```python
def train(images, labels):
    # Training
    return model
```
`predict`: 拿到新图片后, 观察新图片与哪些图片相似, 然后返回*最相似图片*的标签
```python
def predict(model, test_images):
    # Use model to predict labels
    return test_labels
```


