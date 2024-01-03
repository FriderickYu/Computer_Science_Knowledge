import torch


# 实现自己的Pytorch autograd

# 定义torch.autograd.Function的子类并实现forward和backward函数来定义autograd操作
class MyReLU(torch.autograd.Function):
    """
    Implement my own custom autograd Functions
    在前向传播中, 接收了一个上下文对象和一个包含输入的张量, 必须返回一个包含输出的张量;
    并且我们可以使用上下文对象来缓存对象以便在反向传播中使用
    Pytorch中的上下文对象提供了一个存储中间值和参数的地方, 以便在反向传播中使用
    上下文对象允许在前向传播与后向传播之间共享信息, 并且可以在反向传播时访问这些信息以计算梯度
    """
    @staticmethod
    def forward(ctx, x):
        # 保存输入张量x以便在反向传播中使用
        ctx.save_for_backward(x)
        return x.clamp(min=0)

    @staticmethod
    def backward(ctx, grad_output):
        x, = ctx.saved_tensors
        grad_x = grad_output.clone()
        grad_x[x < 0] = 0
        return grad_x

device = torch.device('cpu')


# N代表batch size, D_in代表输入的维度
# H代表隐含层的维度, D_out代表输出的维度

N, D_in, H, D_out = 64, 1000, 100, 10

# 输入x: 64行, 1000列
x = torch.randn(N, D_in)
# 输出y: 64行, 10列
y = torch.randn(N, D_out)

# 初始化权重
w1 = torch.randn(D_in, H, requires_grad=True)
w2 = torch.randn(H, D_out, requires_grad=True)

learning_rate = 1e-6

for t in range(500):
    y_pred = MyReLU.apply(x.mm(w1)).mm(w2)

    loss = (y_pred - y).pow(2).sum()
    print("第%d次迭代的损失率是:%f" %(t, loss.item()))

    loss.backward()

    with torch.no_grad():
        w1 -= learning_rate * w1.grad
        w2 -= learning_rate * w2.grad

        w1.grad.zero_()
        w2.grad.zero_()



