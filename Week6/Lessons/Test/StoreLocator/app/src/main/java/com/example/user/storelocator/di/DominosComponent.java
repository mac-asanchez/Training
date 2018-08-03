package com.example.user.storelocator.di;

import com.example.user.storelocator.view.main.MainActivity;

import dagger.Component;

@Component(modules = DominosModule.class)
public interface DominosComponent {
    void inject(MainActivity activity);
}
