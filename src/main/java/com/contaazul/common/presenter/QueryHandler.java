package com.contaazul.common.presenter;

public interface QueryHandler<TQuery extends Query<TResult>, TResult> {
	TResult handle(TQuery query);
}
