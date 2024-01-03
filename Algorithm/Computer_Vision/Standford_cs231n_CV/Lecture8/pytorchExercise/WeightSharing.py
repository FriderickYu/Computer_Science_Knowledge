import torch
import random

"""
作为动态图和权重共享的一个例子, 下面实现了一个非常奇怪的模型: 一个全连接的ReLU网络, 在每次前向传播时选择一个介于1和4质检的随机数, 并使用这么多个隐藏层,
多次重复使用相同的权重来计算最内层的隐藏层; 对于这个模型, 可以使用普通的Python流控制来实现循环, 并且可以通过简单地多次重复使用相同的模块来定义前向传播中最内层质检的权重共享
"""


class DynamicNet(torch.nn.Module):
    def __init__(self, D_in, H, D_out):
        # 实例化三个nn.Linear实例, 并为其分配成员变量, 将在前向传播中使用
        super(DynamicNet, self).__init__()
        self.input_linear = torch.nn.Linear(D_in, H)
        self.hidden_linear = torch.nn.Linear(H, H)
        self.output_linear = torch.nn.Linear(H, D_out)

    def forward(self, x):
        """
        对于模型的前向传播, 随机选择0, 1, 2或3, 并重复使用hidden_linear多次计算隐藏层
        由于每次前向传播都构建了一个动态计算图, 所以在定义模型的前向传播时, 可以使用普通的Python控制流操作, 比如循环和条件语句
        """
        h_relu = self.input_linear(x).clamp(min=0)
        for _ in range(random.randint(0, 3)):
            h_relu = self.hidden_linear(h_relu).clamp(min=0)
        y_pred = self.output_linear(h_relu)
        return y_pred


N, D_in, H, D_out = 64, 1000, 100, 10
x = torch.randn(N, D_in)
y = torch.randn(N, D_out)

model = DynamicNet(D_in, H, D_out)
loss_fn = torch.nn.MSELoss(reduction='sum')
optimizer = torch.optim.SGD(model.parameters(), lr=1e-4, momentum=0.9)
for t in range(500):
    y_pred = model(x)
    loss = loss_fn(y_pred, y)
    print("第%d次迭代的损失率是:%f" %(t, loss.item()))
    optimizer.zero_grad()
    loss.backward()
    optimizer.step()
