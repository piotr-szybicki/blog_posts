import { RoutingModuleModule } from './routing-module.module';

describe('RoutingModuleModule', () => {
  let routingModuleModule: RoutingModuleModule;

  beforeEach(() => {
    routingModuleModule = new RoutingModuleModule();
  });

  it('should create an instance', () => {
    expect(routingModuleModule).toBeTruthy();
  });
});
