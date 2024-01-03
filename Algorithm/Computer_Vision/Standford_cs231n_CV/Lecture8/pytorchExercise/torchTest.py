import torch

# 同numpyTest, 不过使用的是Pytorch进行实现
# 手动实现正向传播与反向传播, Pytorch tensor跟numpy最大的区别是能在GPU上跑

# We wanna this to run on GPU
# device = torch.device('cuda' if torch.cuda.is_available() else 'cpu')
device = torch.device('cpu')

# N代表batch size, D_in代表输入的维度
# H代表隐含层的维度, D_out代表输出的维度

N, D_in, H, D_out = 64, 1000, 100, 10

# 输入x: 64行, 1000列
x = torch.randn(N, D_in)
# 输出y: 64行, 10列
y = torch.randn(N, D_out)

# 初始化权重
# 标准正态分布, 随机生成[0, 1)之间的随机数(张量), 张量的形状由第一个参数sizes定义
w1 = torch.randn(D_in, H)
w2 = torch.randn(H, D_out)

learning_rate = 1e-6

for t in range(500):
    # Forward pass: predict y, w1*x
    # x.mm(w1), 矩阵乘法 -> 64行100列
    h = x.mm(w1)
    # clamp(x, min, max), 将张量x限制在[min, max]之间, 比min小的元素全部置为min, 比max大的元素全部置为max
    # 例如x = torch.arange(12) => [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]
    # torch.clamp(x, 2, 10)返回[2, 2, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10]
    # relu函数, if(x < 0): y = 0 else: y = x
    h_relu = h.clamp(min=0)
    # 64行10列, h_relu*w2
    y_pred = h_relu.mm(w2)
    # Compute and print loss; 计算出来的loss是tensor, 如果只想要其中的value, 可以使用item()
    # 首先计算损失对于预测值的梯度, 损失函数使用的是欧几里得距离
    loss = (y_pred - y).pow(2).sum()
    print("第%d次迭代的损失率是:%f" %(t, loss.item()))
    # Backprop to compute gradients of w1 and w2 with respect to loss
    grad_y_pred = 2.0 * (y_pred - y)
    grad_w2 = h_relu().t().mm(grad_y_pred)
    grad_h_relu = grad_y_pred.mm(w2.t())
    grad_h = grad_h_relu.clone()
    grad_h[h < 0] = 0
    grad_w1 = x.t().mm(grad_h)

    # update weights using gradient descent
    w1 -= learning_rate * grad_w1
    w2 -= learning_rate * grad_w2




