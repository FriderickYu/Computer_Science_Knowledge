import numpy as np

# 使用numpy包实现两层全连接神经网络, 正向与反向传播

# N代表batch size, D_in代表输入的维度
# H代表隐含层的维度, D_out代表输出的维度

N, D_in, H, D_out = 64, 1000, 100, 10

# 输入x: 64行, 1000列
x = np.random.randn(N, D_in)
# 输出y: 64行, 10列
y = np.random.randn(N, D_out)

# 初始化权重
# 标准正态分布, 随机生成[0, 1)之间的随机数(张量), 张量的形状由第一个参数sizes定义
w1 = np.random.randn(D_in, H)
w2 = np.random.randn(H, D_out)

learning_rate = 1e-6

for t in range(500):
    # Forward pass: predict y, w1*x
    h = x.dot(w1)
    # 激活函数使用relu
    h_relu = np.maximum(h, 0)
    # relu输出*w2
    y_pred = h_relu.dot(w2)

    # compute loss
    loss = np.square(y_pred - y).sum()
    print("第%d次迭代的损失率是:%f" %(t, loss))

    # backprop to compute of w1 && w2
    grad_y_pred = 2.0 * (y_pred - y)
    grad_w2 = h_relu.T.dot(grad_y_pred)
    grad_h_relu = grad_y_pred.dot(w2.T)
    grad_h = grad_h_relu.copy()
    grad_h[h < 0] = 0
    grad_w1 = x.T.dot(grad_h)
    # Update weights
    w1 -= learning_rate * grad_w1
    w2 -= learning_rate * grad_w2



