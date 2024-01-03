import torch


# 有时候需要实现比Sequential更复杂的结构
# 接下来的例子将实现自定义的两层网络作为一个自定义的Module子类

class TwoLayerNet(torch.nn.Module):
    def __init__(self, D_in, H, D_out):
        # 实例化两个nn.Linear模块, 并为其分配成员变量
        super(TwoLayerNet, self).__init__()
        self.linear1 = torch.nn.Linear(D_in, H)
        self.linear2 = torch.nn.Linear(H, D_out)

        #
    def forward(self, x):
        h_relu = self.linear1(x).clamp(min=0)
        y_pred = self.linear2(h_relu)
        return y_pred


N, D_in, H, D_out = 64, 1000, 100, 10
x = torch.randn(N, D_in)
y = torch.randn(N, D_out)

model = TwoLayerNet(D_in, H, D_out)
loss_fn = torch.nn.MSELoss(reduction='sum')
learning_rate = 1e-4
optimizer = torch.optim.SGD(model.parameters(), lr=learning_rate)

for t in range(500):
    y_pred = model(x)
    loss = loss_fn(y_pred, y)
    print("第%d次迭代的损失率是:%f" %(t, loss.item()))

    optimizer.zero_grad()
    loss.backward()
    optimizer.step()
